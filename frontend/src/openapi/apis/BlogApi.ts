/* tslint:disable */
/* eslint-disable */
/**
 * Internal API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 0.1
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import * as runtime from '../runtime'
import {
  BlogPostDto,
  BlogPostDtoFromJSON,
  BlogPostDtoToJSON,
  BlogPostPreviewDto,
  BlogPostPreviewDtoFromJSON,
  BlogPostPreviewDtoToJSON,
} from '../models'

export interface FindPostRequest {
  id: number;
}

/**
 *
 */
export class BlogApi extends runtime.BaseAPI {

  /**
   */
  async findPostRaw(requestParameters: FindPostRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<BlogPostDto>> {
    if (requestParameters.id === null || requestParameters.id === undefined) {
      throw new runtime.RequiredError('id', 'Required parameter requestParameters.id was null or undefined when calling findPost.')
    }

    const queryParameters: any = {}

    const headerParameters: runtime.HTTPHeaders = {}

    const response = await this.request({
      path: `/api/blog/post/{id}`.replace(`{${'id'}}`, encodeURIComponent(String(requestParameters.id))),
      method: 'GET',
      headers: headerParameters,
      query: queryParameters,
    }, initOverrides)

    return new runtime.JSONApiResponse(response, (jsonValue) => BlogPostDtoFromJSON(jsonValue))
  }

  /**
   */
  async findPost(requestParameters: FindPostRequest, initOverrides?: RequestInit): Promise<BlogPostDto> {
    const response = await this.findPostRaw(requestParameters, initOverrides)
    return await response.value()
  }

  /**
   */
  async findPublishedPostsIdsRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<BlogPostPreviewDto>>> {
    const queryParameters: any = {}

    const headerParameters: runtime.HTTPHeaders = {}

    const response = await this.request({
      path: `/api/blog/posts`,
      method: 'GET',
      headers: headerParameters,
      query: queryParameters,
    }, initOverrides)

    return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(BlogPostPreviewDtoFromJSON))
  }

  /**
   */
  async findPublishedPostsIds(initOverrides?: RequestInit): Promise<Array<BlogPostPreviewDto>> {
    const response = await this.findPublishedPostsIdsRaw(initOverrides)
    return await response.value()
  }

}
